/*
 * Copyright 2013 SpringSource.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.grails.scaffolding;

import grails.build.logging.GrailsConsole;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.groovy.grails.commons.GrailsApplication;
import org.codehaus.groovy.grails.commons.GrailsDomainClass;
import org.codehaus.groovy.grails.commons.GrailsDomainClassProperty;
import org.codehaus.groovy.grails.plugins.GrailsPluginInfo;
import org.codehaus.groovy.grails.plugins.GrailsPluginManager;
import org.codehaus.groovy.grails.plugins.GrailsPluginUtils;
import org.codehaus.groovy.grails.plugins.PluginManagerAware;
import org.codehaus.groovy.runtime.IOGroovyMethods;
import org.codehaus.groovy.runtime.StringGroovyMethods;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public abstract class AbstractGrailsTemplateGenerator implements GrailsTemplateGenerator, ResourceLoaderAware, PluginManagerAware {

	protected static final Log log = LogFactory.getLog(AbstractGrailsTemplateGenerator.class);

	protected String basedir = ".";
	protected boolean overwrite = false;
	protected SimpleTemplateEngine engine = new SimpleTemplateEngine();
	protected ResourceLoader resourceLoader;
	protected Template renderEditorTemplate;
	protected String domainSuffix = "";
	protected GrailsPluginManager pluginManager;
	protected GrailsApplication grailsApplication;

	protected enum GrailsControllerType {
		DEFAULT,
		RESTFUL,
		ASYNC
	}

	protected AbstractGrailsTemplateGenerator(ClassLoader classLoader) {
		engine = new SimpleTemplateEngine(classLoader);
	}

	public void generateViews(GrailsDomainClass domainClass, String destDir) throws IOException {
		Assert.hasText(destDir, "Argument [destdir] not specified");

		File viewsDir = new File(destDir, "grails-app/views/" + domainClass.getPropertyName());
		if (!viewsDir.exists()) {
			viewsDir.mkdirs();
		}

		for (String name : getTemplateNames()) {
			if (log.isInfoEnabled()) {
				log.info("Generating [" + name + "] view for domain class [" + domainClass.getFullName() + "]");
			}
			generateView(domainClass, name, viewsDir.getAbsolutePath());
		}
	}

	public void generateController(GrailsControllerType controllerType, GrailsDomainClass domainClass, String destDir) throws IOException {
		Assert.hasText(destDir, "Argument [destdir] not specified");

		if (domainClass == null) {
			return;
		}

		String fullName = domainClass.getFullName();
		String pkg = "";
		int pos = fullName.lastIndexOf('.');
		if (pos != -1) {
			// Package name with trailing '.'
			pkg = fullName.substring(0, pos + 1);
		}

		File destFile = new File(destDir, "grails-app/controllers/" + pkg.replace('.', '/') +
				domainClass.getShortName() + "Controller.groovy");
		if (canWrite(destFile)) {
			destFile.getParentFile().mkdirs();

			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(destFile));
				generateController(controllerType, domainClass, writer);
				try {
					writer.flush();
				} catch (IOException ignored) {}
			}
			finally {
				IOGroovyMethods.closeQuietly(writer);
			}

			log.info("Controller generated at [" + destFile + "]");
		}
	}

	@Override
	public void generateController(GrailsDomainClass domainClass, String destDir) throws IOException {
		generateController(GrailsControllerType.DEFAULT, domainClass, destDir);
	}

	@Override
	public void generateRestfulController(GrailsDomainClass domainClass, String destDir) throws IOException {
		generateController(GrailsControllerType.RESTFUL, domainClass, destDir);
	}

	@Override
	public void generateAsyncController(GrailsDomainClass domainClass, String destDir) throws IOException {
		generateController(GrailsControllerType.ASYNC, domainClass, destDir);
	}

	public void generateView(GrailsDomainClass domainClass, String viewName, Writer out) throws IOException {
		String templateText = getTemplateText(viewName + ".gsp");

		if (!StringUtils.hasLength(templateText)) {
			return;
		}

		GrailsDomainClassProperty multiPart = null;
		for (GrailsDomainClassProperty property : domainClass.getProperties()) {
			if (property.getType() == Byte[].class || property.getType() == byte[].class) {
				multiPart = property;
				break;
			}
		}

		String packageName = StringUtils.hasLength(domainClass.getPackageName()) ? "<%@ page import=\"" + domainClass.getFullName() + "\" %>" : "";
		Map<String, Object> binding = createBinding(domainClass);
		binding.put("packageName", packageName);
		binding.put("multiPart", multiPart);
		binding.put("propertyName", getPropertyName(domainClass));

		generate(templateText, binding, out);
	}

	protected abstract Object getRenderEditor();

	@Override
	public void generateView(GrailsDomainClass domainClass, String viewName, String destDir) throws IOException {
		File destFile = new File(destDir, viewName + ".gsp");
		if (!canWrite(destFile)) {
			return;
		}

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(destFile));
			generateView(domainClass, viewName, writer);
			try {
				writer.flush();
			}
			catch (IOException ignored) {}
		}
		finally {
			IOGroovyMethods.closeQuietly(writer);
		}
	}

	@Override
	public void generateController(GrailsDomainClass domainClass, Writer out) throws IOException {
		generateController(GrailsControllerType.DEFAULT, domainClass, out);
	}

	protected void generateController(GrailsControllerType controllerType, GrailsDomainClass domainClass, Writer out) throws IOException {
		String templateText = null;
		switch (controllerType) {
		case DEFAULT:
			templateText = getTemplateText("Controller.groovy");
			break;
		case RESTFUL:
			templateText = getTemplateText("RestfulController.groovy");
			break;
		case ASYNC:
			templateText = getTemplateText("AsyncController.groovy");
			break;
		}

		Map<String, Object> binding = createBinding(domainClass);
		binding.put("packageName", domainClass.getPackageName());
		binding.put("propertyName", getPropertyName(domainClass));

		generate(templateText, binding, out);
	}

	@Override
	public void generateRestfulTest(GrailsDomainClass domainClass, String destDir) throws IOException {
		generateTest(domainClass, destDir, "RestfulSpec.groovy");
	}

	@Override
	public void generateAsyncTest(GrailsDomainClass domainClass, String destDir) throws IOException {
		generateTest(domainClass, destDir, "AsyncSpec.groovy");
	}

	@Override
	public void generateTest(GrailsDomainClass domainClass, String destDir) throws IOException {
		generateTest(domainClass, destDir, "Spec.groovy");
	}

	private void generateTest(GrailsDomainClass domainClass, String destDir, String templateName) throws IOException {
		File destFile = new File(destDir, domainClass.getPackageName().replace('.', '/') + '/' + domainClass.getShortName() + "ControllerSpec.groovy");
		if (!canWrite(destFile)) {
			return;
		}

		String templateText = getTemplateText(templateName);
import sys
import smtplib

sender = 'stulance.jobs1@yahoo.com'
receivers = sys.argv[0]
password = '******'

message = """From: From Person <from@fromdomain.com>
To: To Person <to@todomain.com>
Subject: SMTP e-mail test

This is a test e-mail message.
"""

try:
 smtpObj = smtplib.SMTP('smtp.mail.yahoo.com',587)
 smtpObj.ehlo()
 smtpObj.starttls()
 smtpObj.login(sender, password)
 smtpObj.sendmail(sender, receivers, message)    
 print ("Successfully sent email")
except SMTPException:
 print ("Error: unable to send email")

import sys
import smtplib

sender = 'badal.jain77@gmail.com'
receivers = ['badal.jain@sjsu.edu']
password = 'bj9691718895'

message = """From: From Person <from@fromdomain.com>
To: To Person <to@todomain.com>
Subject: SMTP e-mail test

This is a test e-mail message.
"""

try:
 smtpObj = smtplib.SMTP('smtp.gmail.com',587)
 smtpObj.ehlo()
 smtpObj.starttls()
 smtpObj.login(sender, password)
 smtpObj.sendmail(sender, receivers, message)    
 print ("Successfully sent email")
except SMTPException:
 print ("Error: unable to send email")
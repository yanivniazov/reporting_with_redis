Reporting Tool with Redis

This tool exposes an API to add a new report in the future.

Example of how to run the reporting :
http://localhost:8080/echoAtTime?time=1582402550336&message=anyMessage

this Reporting tool is configured to run on Redis locally.
The reporting tool will generate logs.

I used ZSORT data type in order to optimise the searching engine and get the earlieset report to generate everyTime.
the score is the time of each report.
I also add some unit tests to validate the tool.

INIT:
When you start the Tool, it will search all old reports there were supposed to be sent and send them immidatly.

there are some validation on the API :
1. you cant add a negetive number on time.
2. you can't add a time in the past.
3. you can't send an empty message to the API.

Good Luck.


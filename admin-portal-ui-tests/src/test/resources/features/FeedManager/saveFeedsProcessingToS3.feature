Feature: While uploading of feeds in Admin Portal, some of the feeds are not successfull ie status Failed or Inprogress
# And manually identifying of unsuccessful feeds are tedious.
# Hence API has to be created for identifying the same and uploading in S3 bucket.

Scenario: Uploading of unsuccessful feeds in S3 bucket
Given API /saveFeedsProcessingToS3 is called
Then All unsuccessful feeds for the past 12 hours will be uploaded in S3 bucket

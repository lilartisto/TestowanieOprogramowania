Meta:

Narrative:
As a user
I want to encode an image in another image
So that it isn't visible unless decoded

Scenario: encoding image
Given two readable, not empty images
When I encode one within another
Then I get new image that has encoded image
Meta:

Narrative:
As a user
I want to encode an image in another image
So that it isn't visible unless decoded

Scenario: scenario encoding image
Given Main Class with first argument as 1
And FileManager Class
When path to a source image as <sourcePath>
And path to a secret image as <secretPath>
Then I get modified result image at <savePath>


Scenario: scenario decoding image
Given Main Class with first argument as 0
And FileManager Class
When path to a source image as <sourcePath>
Then I get modified result image at <savePath>
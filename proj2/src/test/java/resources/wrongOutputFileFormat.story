Narrative:
As a user
I want to be informed about wrong format of output file
So that it is clear why I cannot save output file

Scenario: user want to save image as a text file

Given correct paths to source and secret image
When secret image is encoded into source image
Then saving image as a text file should inform about wrong file format
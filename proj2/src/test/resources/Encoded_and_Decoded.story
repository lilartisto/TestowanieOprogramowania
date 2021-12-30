Meta:

Narrative:
As a user
I want to encode an image in another image
So that it isn't visible unless decoded

Scenario: scenario encoding and decoding an image
Given correct paths to source and secret images
When secret image is encoded into source image
Then saving image should give properly encoded image
And result image should be properly decoded into 2 bit image
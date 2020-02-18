Feature: IAT Admin HR - templates - images upload resizing
  As IAT Admin user
  I want to be able to upload image
  To be able to store images for templates purpose and properly resize them

  @Regression @PositiveCase @Templates
  Scenario Outline: Image Processing - upload image into system
    Given IAT Admin user is logged in with credentials '<credentials>'
    When Admin upload image file into the system '<image>', '<rescale>'
    Then Image file should be uploaded '<rescale>', '<warningMessage>'

  @qa
    Examples:
      | credentials                                      | image                       | rescale | warningMessage                                                                               |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | pngImage.png                | true    | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | twoMbImage.png              | true    | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | whiteQhdResolutionImage.jpg | true    | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | hdImage.jpg                 | true    | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | lowResolutionImage.jpg      | true    | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | gif500x500.gif              | true    | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | gif800x600_v1.gif           | true    | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | gif1024x1024.gif            | true    | Maximum gif resolution is 800 x 600. It will be shown as a static image if larger than this. |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | pngImage.png                | false   | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | twoMbImage.png              | false   | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | whiteQhdResolutionImage.jpg | false   | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | hdImage.jpg                 | false   | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | lowResolutionImage.jpg      | false   | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | gif500x500.gif              | false   | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | gif800x600_v1.gif           | false   | null                                                                                         |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | gif1024x1024.gif            | false   | Maximum gif resolution is 800 x 600. It will be shown as a static image if larger than this. |


  @Regression @PositiveCase @Templates
  Scenario Outline: Image Processing - upload image with invalid format or size
    Given IAT Admin user is logged in with credentials '<credentials>'
    When Admin upload image file with invalid format or size '<image>', '<rescale>', '<expResponseCode>'

  @qa
    Examples:
      | credentials                                      | image           | rescale | expResponseCode |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | fiveMbImage.jpg | true    | 413             |
      | api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd | gif800x600.gif  | true    | 413             |
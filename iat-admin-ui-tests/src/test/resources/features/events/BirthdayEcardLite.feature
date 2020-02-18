Feature: IAT ADMIN - lite version of the send ecard UI for Birthday

@eCardLite
Scenario Outline: Send ecard will be enabled only when user is selected

Given IAT Admin user is logged in with credentials '<login_password>'
When Birthday event is selected
And select userA "<userA>"
Then send ecard will be enabled with userA
Then check userA envelope image changes to green
And again select userB "<userB>"
Then send ecard will be enabled with userB
Then check userA envelope image changes to blue
Then check userB envelope image changes to green
And click Cancel button
Then check userB envelope image changes to blue
Then userB details will be removed from send ecard and ecard will be disabled

@qa
    Examples:
      | login_password  | userA | userB |
	  | leafleveldept@gmail.com,password  | userA | userB |

	  
@eCardLite
Scenario Outline: Send ecard button will be enabled only when all the details are available

Given IAT Admin user is logged in with credentials '<login_password>'
When Birthday event is selected
And select userA
Then send ecard will be enabled with userA "<userA>" 
And select image (caroseul)
And select reason "<reason>"
And add personnel message "<message>"	
And add epoints value "<value>"
Then Send ecard button will be enabled and click on it
Then ecard will be sent and all the selected details has to be cleared and grayed out
Then message Points have been sent will be displayed on top right on desktop, and botton for mobile
Then check userA envelope image changes to blue


@qa
    Examples:
      | login_password	|	userA	|	reason	|	message		|	value	|
	  | leafleveldept@gmail.com,password  |	userA	|	reason	|	message	|	value	|



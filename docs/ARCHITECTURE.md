### Architecture:

We divide our application into 3-tier architecture. There are 6 main java packages:  'application', 'business', 'objects', 'persistence', 'presentation'.




## Domain-Specific Object

- Project
- Task
- User

## Presentation Layer

- AddProjectActivity
- InviteSent
- MainActivity
  - communicate between Presentation and Logic layer
  - responsible for connecting different elements
- NameErrorActivity
- NotificationsActivity
- ProjectActivity
- ProjectList
- ProjectPage
- RecipientInfoActivity
- RespondNotifActivity
- SendToUser
- SentInviteActivity
- UserController

## Business Layer

- AccountService
- NotifService
- ProjectService
- TaskService
- UserService

## Persistence Layer

- UserImp
- AccountImp
- NotifImp
- PersistenceException
- ProjectImp
- TaskImp


![Architecture diagram](https://app.diagrams.net/#G1O1YgvnImUHyDjcPfwReyGTl-Sjkchb_3)

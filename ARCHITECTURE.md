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


![Architecture diagram](https://code.cs.umanitoba.ca/3350-winter-2021-a03/youtilities-comp3350-a03-group3/-/blob/master/docs/Architecture.md)

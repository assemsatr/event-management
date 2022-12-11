# event-management-spring boot
Simple Event master app where a user can register and view/filer/paginate events.

A user is able to register (user/admin roles). 
A user/admin can add an eventS and attend an interesting event.
DTO CRUD pattern is used is implemented as value object between client and app.
event,user,role,attendees entities.
A list can be viewed as paginated/filtered list. 
Spring basic security configuration is implemented. 
Retrieved all to-dos by logged in users.
Mapping; ManyToOne, ManyToMany implemented.
integrated with mail server to send emails and mail scheduling config.  
used liquibase as db schema management.

# Reepay API examples

Example project showing samples of the Reepay API use for essential subscription handling operations. The project is written in Java using the Spring Boot framework.
**This example does not do any frontend validation nor any nice error handling. This example project is purely for demonstration of how a java project could look like when using the Reepay API**

## Prerequisites

* Java 8
* Maven 3.x
* Reepay test account ([get a free test account](https://signup.reepay.com/)) with at least one subscription plan configured

## Building

    mvn clean install

## Running

A public and a private key must be provided as well as the handle for the plan you wish to test this example on.

    mvn spring-boot:run -Dpublic_key=pub_a14c9c530076ac4abfcbdded1fc68dbc -Dprivate_key=priv_105b4a558fdf057833e31ec80b9572fe -Dplan_handle=myplanhandle

If the application does not automatically open in your browser, go to http://localhost:8080/

If the application cannot start, verify that the keys are correct, and a plan with the given handle exists.

## The application

### Sign-up

The sign-up page will show you a form. All fields are required. Test cards can be found [here](https://docs.reepay.com/api/#testing).

When you sign-up, a customer will be created in the local database and the customer will be subscribed to the plan through Reepay.

- **There are no one-to-one mapping between the local database and Reepay. The local database is reset every time the application is restarted**
- **There are no length or complexity restriction on the password**
- **In this example, you can't have two customers registered with the same email address**

## Signed-up customers

When one or more customers are signed-up they will be shown under the tab "Registered customers"

- **This tab is for testing purposes only. You should never expose such data**

## Signing in to _my page_

When you have signed-up you can sign in and view various details.
If you click on the subscription handle you should be able to view information about the plan to which you are subscribed.
Here you can unsubscribe, add a coupon to your subscription and if you click on the subscription handle again, you will be able to view all your invoices, and information about the card you have associated with the subscription.

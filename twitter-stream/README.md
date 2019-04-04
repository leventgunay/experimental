# chatngo

Here you find an experimental and full stack web application featuring Twitter Streaming API. 

## Tools and technologies

Please find following software tools and frameworks being involved;

- Java 8 // back end runtime
- Gradle // back end dependency management and project builder
- Spring Framework // back end web framework
- Spring Integration Social Twitter // library implementing twitter api

- NodeJs // front end development runtime (npm 5.6.0)
- React Scripts // front end build tool
- ReactJs // single page web app framework
- React-MD // UI component library


## Running on local

Before building and starting the application, you will need to replace Twitter OAuth Credentials as follows;

src/main/resources/application.properties
```
twitter.appId={{put consumerKey here}}
twitter.appSecret={{put consumerSecret here}}

twitter.accessToken={{put accessToken here}}
twitter.accessTokenSecret={{put accessTokenSecret here}}
```

By then, you could have both back and front end installed, built and deployed via; 

  ~$ npm start

(It may take a while for the first time.)


*http://localhost:8080/* will be serving the application UI.


## Running tests

  ~$ npm test


## Running on docker

  ~$ npm run build
  ~$ gradle bootJar
  ~$ docker build -t exercise/twitter .
  ~$ docker run -p 8080:8080 exercise/twitter
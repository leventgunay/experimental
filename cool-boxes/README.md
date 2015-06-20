cool-boxes
==========


Getting Started
---------------

This app includes a simple html layout experiment which demonstrates basic markup manipulations;

Here are some tools and technologies used;

* NodeJS Runtime
* Grunt Task Runner
* Karma, Jasmine, Protractor
* JQuery, Bootstrap CSS



#### Environment Setup

The implementation requires [NodeJs](http://nodejs.org/) runtime configuration with [Grunt](http://gruntjs.com/) task runner and packages, so
you may want to download and install NodeJS on your local if you don't have it handy. When node and npm (this comes with node installation) are ready;

    ~$ npm install

We should have all packages loaded here. And let's clean and build;

    ~$ grunt clean build

And air;

    ~$ grunt run

Even shorter to have clean/build/run together by;

    ~$ grunt all

Now, application should be served under *http://localhost:8080/*



#### Development

For development purposes, you can also use following execution and ports;

    ~$ grunt dev


*http://localhost:9000/* will be serving the application.

*http://localhost:35729/* will be listening js/css/html changes to reflect them through livereload.



#### Unit Testing

Following executions (depending on your OS and environmental variables) are built in for unit testing;

    ~$ grunt test
    ~$ grunt karma:phantom
    ~$ grunt karma:chrome
    ~$ grunt karma:firefox
    ~$ grunt karma:safari
    ~$ grunt karma:opera
    ~$ grunt karma:ie



#### Automation Testing

Implementation includes protractor automation and related configuration. You can try following executions depending on OS and browser preferences.

    ~$ grunt itest
    ~$ grunt protractor:chrome
    ~$ grunt protractor:firefox (not ready)



#### Browser Support

* Chrome/FireFox/Opera/Safari (Most of the latest releases are fine. Latest stable releases are tested.)
* IE9+ (Hasn't been tested.)



#### Contribution

Please feel free to contact within any feedback, idea or pull request to discuss possible improvements and better practices.
exports.config = {
  directConnect: true,

  capabilities: {
    'browserName': 'chrome'
  },

  beforeLaunch: function() {
    // check out webdriver
    var exec = require('child_process').execSync;
    console.log(exec("./node_modules/grunt-protractor-runner/node_modules/protractor/bin/webdriver-manager update --chrome", { encoding: 'utf8' }));
  },

  onPrepare: function() {

    global.basep = require('../lib/baseptor');

    // angular not needed.
    basep.disableNgSync();

    // navigate app
    basep.go();

  }
};

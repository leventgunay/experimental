exports.config = {
  directConnect: true,

  capabilities: {
    'browserName': 'chrome'
  },

  beforeLaunch: function() {
    console.log('Checking chrome webdriver.. ');
    console.log('./node_modules/grunt-protractor-runner/node_modules/.bin/webdriver-manager update --chrome');
    console.log('If it fails or times out, you might want to execute the command above manually and re-run "grunt itest"');

    var exec = require('child_process').execSync;
    exec("./node_modules/grunt-protractor-runner/node_modules/.bin/webdriver-manager update --chrome", { encoding: 'utf8', timeout: 300000 });
  },

  onPrepare: function() {

    global.basep = require('../lib/baseptor');

    // angular not needed.
    basep.disableNgSync();

    // navigate app
    basep.go();

  }
};

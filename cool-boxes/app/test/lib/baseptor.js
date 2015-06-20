module.exports = (function(ptor, dr) {
    var base = {
        disableNgSync: function() {
            dr.ignoreSynchronization = true;
        },

        enableNgSync: function() {
            dr.ignoreSynchronization = false;
        }
    }; return base;
})(protractor, browser);
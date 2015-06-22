module.exports = (function(ptor, dr) {
    var base = {
        uri: 'http://localhost:9090/',

        disableNgSync: function() {
            dr.ignoreSynchronization = true;
        },

        enableNgSync: function() {
            dr.ignoreSynchronization = false;
        },

        getAbsUrl: function(p) {
            var uri = base.uri;
            if(p && p.length) { return uri + p ; }
            return uri;
        },

        go: function(p) {
            var uri = base.getAbsUrl(p);
            dr.get(uri);
//            base.waitLoading(uri);
        },

        waitLoading: function(u) {
            dr.driver.wait(function() {
                return dr.driver.getCurrentUrl(function(url) {
                    console.log(url + ' -- ' + u );
                    return url === u;
                });
            });
        }
    }; return base;
})(protractor, browser);
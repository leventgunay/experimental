(function($) {

    var list = [], removedCount = 0, last = 0;

    function message(s, severity) {
        var mel = $('.page-header .message:first'), mbody = mel.children('.panel-body:first');

        mbody.removeClass('bg-info bg-danger bg-success');
        mel.removeClass('panel-info panel-danger panel-success');

        if(severity === 'danger') {
            mbody.addClass('bg-danger');
            mel.addClass('panel-danger');
        } else if(severity === 'success'){
            mbody.addClass('bg-success');
            mel.addClass('panel-success');
        } else {
            // default info
            mbody.addClass('bg-info');
            mel.addClass('panel-info');
        }

        mbody.text(s);
        mel.finish().fadeIn({
            duration: 500,
            done: function() {
                mel.delay(300).fadeOut({ duration: 200 });
            }
        });
    }

    function insertNewBox(e) {
        var box = $(this),
        nbox = box.clone();

        box.parent().removeClass('bg-light-gray').addClass('bg-dark-gray');

        message('Box Inserted: ' + add(box, nbox), 'success');

        e.preventDefault();
    }

    function deleteBox(e) {
        var el = $(this), box = el.parents('.cool-box:first');

        box.parent().removeClass('bg-dark-gray').addClass('bg-light-gray');

        if(box.index() || !box.is(':last-child')) {
            message('Box Deleted: ' + remove(box));
        } else {
            message('Never eat the last donut!', 'danger');
        }

        e.stopPropagation();
        e.preventDefault();
    }

    function setIds(prev, curr, next) {
        var p = prev && prev.data('id'),
            c = curr && curr.data('id'),
            n = next && next.data('id'),
            isRemoving = curr.is(':visible') && prev && true,
            result = [];

        if(isRemoving) {
            result = list.splice(list.indexOf(c), 1);
        } else {
            result = list.splice(list.length && (list.indexOf(p) + 1), 0, c);
        }

        store('coolboxlist', list);

        if(n) {
            next.find('.box-body .prev:first').text(isRemoving ? (p || '') : c);
        } if(p) {
            prev.find('.box-body .next:first').text(isRemoving ? (n || '') : c);
        } if(c && !isRemoving) {
            curr.find('.box-body .prev:first').text(p || '');
            curr.find('.box-body .next:first').text(n || '');
            curr.find('.box-heading span:first').text(c);
            return c; // added
        } return result[0]; // removed
    }

    function add(prev, el, id) {
        updateProgress(++last, removedCount);

        var newId = id || last;

        el.data('id', newId);

        setIds(prev, el, prev && prev.next());

        el.insertAfter(prev);

        return newId;
    }

    function remove(box) {
        var prev = box.prev(), next = box.next();

        var removedId = setIds(prev, box, next);

        box.remove();

        updateProgress(last, ++removedCount);

        store('coolboxRemovedCount', removedCount);

        return removedId;
    }

    function updateProgress(totalC, removedC) {
        var pie = 100 / totalC, existingC = totalC - removedC;

        $('.container .progress .box-count-existing:first')
                    .css({ width: pie * existingC + '%'}).children('span:first').text(existingC);

        $('.container .progress .box-count-removed:first')
                    .css({ width: pie * removedC + '%'}).children('span:first').text(removedC);
    }

    function store(name, val) {
        if(localStorage) {
            if(Array.isArray(val)) {
                localStorage[name] = val.join('-');
            } else if(typeof val === 'object') {
                localStorage[name] = JSON.stringify(val);
            } else {
                localStorage[name] = val;
            }
        }
    }

    function restore(name) {
        return localStorage && localStorage[name];
    }

    function init() {
        var rlist = restore('coolboxlist'),
            templ = $('.container-one .container-two .cool-box:first'),
            prev = undefined;

        var removedC = restore('coolboxRemovedCount');
        if(removedC && removedC.length) {
            removedCount = parseInt(removedC);
        }

        if(rlist && rlist.length) {
            rlist = rlist.split('-');
            last = rlist.length + removedCount;
            for(var ind = 0; ind < rlist.length; ind++) {
                add(prev, templ, rlist[ind]); // restore one by one
                prev = templ; templ = prev.clone();
            } delete rlist;
        } else {
            add(prev, templ); // only
        }
    }

    $(function() {

        init();

        $('.container-one .container-two').delegate('.cool-box', 'click.insertBox', insertNewBox);

        $('.container-one .container-two').delegate('.cool-box .box-heading .close', 'click.deleteBox', deleteBox);

    });

})(jQuery);
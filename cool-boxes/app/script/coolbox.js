(function($) {

    function insertNewBox() {
        var el = $(this),
        nel = el.clone();
        push(el, nel);
    }

    function deleteBox(e) {
        var el = $(this);
        pop(el.parents('.cool-box:first'));
        e.stopPropagation();
    }

    function push(prev, next) {
        next.insertAfter(prev);
    }

    function pop(box) {
        box.remove();
    }

    $(function() {

        $('.container-one .container-two').delegate('.cool-box', 'click.insertBox', insertNewBox);

        $('.container-one .container-two').delegate('.cool-box .box-heading .close', 'click.deleteBox', deleteBox);

    });

})(jQuery);
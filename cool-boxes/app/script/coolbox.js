(function($) {

    function insertNewBox() {
        var el = $(this),
        nel = el.clone();

        nel.insertAfter(el);
    }

    $(function() {

        $('.container-one .container-two').delegate('.cool-box', 'click.insertion', insertNewBox);

    });

})(jQuery);
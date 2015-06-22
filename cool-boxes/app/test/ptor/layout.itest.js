describe('Test layout..', function() {

    it('Header should be ok..', function() {

        var header = $('.container > .page-header > .row');

        expect(header.isPresent()).toBe(true);
        expect(header.isDisplayed()).toBe(true);

    });

    it('Progress bar should be ok..', function() {

        var progress = $('.container > .progress');
        var progressExisting = progress.$('.progress-bar.box-count-existing');
        var progressDeleted = progress.$('.progress-bar.box-count-removed');

        expect(progress.isPresent()).toBe(true);
        expect(progress.isDisplayed()).toBe(true);

        expect(progressExisting.isPresent()).toBe(true);
        expect(progressExisting.isDisplayed()).toBe(true);
        expect(progressExisting.getText()).toEqual('1 Existing');

        expect(progressDeleted.isPresent()).toBe(true);
//        expect(progressDeleted.isDisplayed()).toBe(false);
//        expect(progressDeleted.getText()).toEqual('0 Deleted');

    });

    it('Containers should be ok..', function() {

        var cont1 = $('.container > .container-one');
        var cont2 = cont1.$('.container-two');

        expect(cont1.isPresent()).toBe(true);
        expect(cont1.isDisplayed()).toBe(true);

        expect(cont2.isPresent()).toBe(true);
        expect(cont2.isDisplayed()).toBe(true);

    });

    it('Footer should be ok..', function() {

        var footer = $('.container > .footer');

        expect(footer.isPresent()).toBe(true);
        expect(footer.isDisplayed()).toBe(true);

    });
});
describe("Test initials..", function () {

    it("Should have third party functionality ready..", function () {

        expect(jQuery).toBeDefined();
        expect(typeof jQuery).toBe('function');

        expect(jQuery()).toBeDefined();
        expect(typeof jQuery()).toBe('object');

        expect(jQuery().delegate).toBeDefined();
        expect(typeof jQuery().delegate).toBe('function');

        expect(jQuery().parent).toBeDefined();
        expect(typeof jQuery().parent).toBe('function');

        expect(jQuery().addClass).toBeDefined();
        expect(typeof jQuery().addClass).toBe('function');

        expect(jQuery().removeClass).toBeDefined();
        expect(typeof jQuery().removeClass).toBe('function');

        expect(jQuery().css).toBeDefined();
        expect(typeof jQuery().css).toBe('function');

        expect(jQuery().children).toBeDefined();
        expect(typeof jQuery().children).toBe('function');

        expect(jQuery().remove).toBeDefined();
        expect(typeof jQuery().remove).toBe('function');

        expect(jQuery().next).toBeDefined();
        expect(typeof jQuery().next).toBe('function');

        expect(jQuery().prev).toBeDefined();
        expect(typeof jQuery().prev).toBe('function');

        expect(jQuery().insertAfter).toBeDefined();
        expect(typeof jQuery().insertAfter).toBe('function');

        expect(localStorage).toBeDefined();
        expect(localStorage.clear).toBeDefined();
        expect(typeof localStorage.clear).toBe('function');

    });

    it("Should have own libs ready..", function () {

        expect(CoolBox).toBeDefined();
        expect(CoolBox.onLoad).toBeDefined();
        expect(CoolBox.message).toBeDefined();
        expect(CoolBox.insertNewBox).toBeDefined();
        expect(CoolBox.deleteBox).toBeDefined();
        expect(CoolBox.setIds).toBeDefined();
        expect(CoolBox.add).toBeDefined();
        expect(CoolBox.remove).toBeDefined();
        expect(CoolBox.updateProgress).toBeDefined();
        expect(CoolBox.store).toBeDefined();
        expect(CoolBox.restore).toBeDefined();
        expect(CoolBox.init).toBeDefined();

    });

    it("Should store and restore state..", function () {

        expect(CoolBox.store).not.toThrow();

        localStorage.clear();
        expect(localStorage.testState1).toBeUndefined();

        CoolBox.store('testState1', 'test');

        expect(localStorage.testState1).toBeDefined();
        expect(localStorage.testState1).toEqual('test');

        var restoredVal = CoolBox.restore('testState1');

        expect(restoredVal).toBeDefined();
        expect(restoredVal).toEqual('test');

    });

    it("Should not add box..", function () {

        spyOn(CoolBox, 'updateProgress');
        spyOn(CoolBox, 'setIds');
        spyOn(jQuery.fn, 'data');
        spyOn(jQuery.fn, 'insertAfter');

        expect(CoolBox.add()).toBeUndefined();
        expect(CoolBox.add(null, undefined, 12)).toBeUndefined();
        expect(CoolBox.add({}, undefined, 12)).toBeUndefined();
        expect(CoolBox.add({}, undefined, 12)).toBeUndefined();
        expect(CoolBox.add({}, undefined, null)).toBeUndefined();
        expect(CoolBox.add(null, null, 12)).toBeUndefined();
        expect(CoolBox.add({}, null, 12)).toBeUndefined();
        expect(CoolBox.add({}, null, 12)).toBeUndefined();
        expect(CoolBox.add({}, null, null)).toBeUndefined();
        expect(CoolBox.add(null, {}, 12)).toBeUndefined();
        expect(CoolBox.add({}, {}, 12)).toBeUndefined();
        expect(CoolBox.add({}, {}, 12)).toBeUndefined();
        expect(CoolBox.add({}, {}, null)).toBeUndefined();

        expect(CoolBox.updateProgress).not.toHaveBeenCalled();
        expect(CoolBox.setIds).not.toHaveBeenCalled();
        expect(jQuery.fn.data).not.toHaveBeenCalled();
        expect(jQuery.fn.insertAfter).not.toHaveBeenCalled();

    });

    it("Should add box without prev..", function () {

        spyOn(CoolBox, 'updateProgress').and.returnValue();
        spyOn(CoolBox, 'setIds').and.returnValue(12);
        spyOn(jQuery.fn, 'data').and.returnValue();
        spyOn(jQuery.fn, 'insertAfter').and.returnValue();

        var fakeBoxElement = jQuery('<div></div>');

        CoolBox.add(undefined, fakeBoxElement);

        expect(CoolBox.updateProgress).toHaveBeenCalledWith(1, 0);
        expect(CoolBox.setIds).toHaveBeenCalledWith(undefined, fakeBoxElement, undefined);
        expect(jQuery.fn.data).toHaveBeenCalledWith('id', 1);
        expect(jQuery.fn.insertAfter).toHaveBeenCalledWith(undefined);

    });

    it("Should add box with prev..", function () {

        spyOn(CoolBox, 'updateProgress').and.returnValue();
        spyOn(CoolBox, 'setIds').and.returnValue(12);
        spyOn(jQuery.fn, 'data').and.returnValue();
        spyOn(jQuery.fn, 'insertAfter').and.returnValue();

        var fakeBoxElement = jQuery('<div></div>');
        var fakePrevElement = jQuery('<div></div>');

        CoolBox.add(fakePrevElement, fakeBoxElement);

        expect(CoolBox.updateProgress).toHaveBeenCalledWith(2, 0);
        expect(CoolBox.setIds).toHaveBeenCalledWith(fakePrevElement, fakeBoxElement, fakePrevElement.next());
        expect(jQuery.fn.data).toHaveBeenCalledWith('id', 2);
        expect(jQuery.fn.insertAfter).toHaveBeenCalledWith(fakePrevElement);

    });

})
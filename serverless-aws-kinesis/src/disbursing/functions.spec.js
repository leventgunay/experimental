var { disbursingLoan } = require('./functions')

describe('testing disbursing functions', () => {
    it('initials', async () => {
        expect(disbursingLoan).toBeDefined()
    })
})

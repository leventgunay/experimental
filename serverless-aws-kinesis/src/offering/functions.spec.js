jest.mock('../core/crud')
jest.mock('../core/models/loan')

const { Loan } = require('../core/models/loan')
const { create, deleteBy, readAll } = require('../core/crud')
const { createLoan, deleteLoan, listLoans } = require('./functions')

describe('testing functions', () => {
    it('create to pass through model', async () => {
        createLoan({
            pathParameters: { anotherParam: 3 },
        })

        expect(create).toBeCalledWith(Loan, { amount: undefined, companyId: undefined })

        createLoan({
            pathParameters: { amount: 2, anotherParam1: 3 },
        })

        expect(create).toBeCalledWith(Loan, { amount: 2, companyId: undefined })
    })

    it('delete to pass through model', async () => {
        deleteLoan({
            pathParameters: { anotherParam: 3 },
        })

        expect(deleteBy).toBeCalledWith(Loan, undefined)

        deleteLoan({
            pathParameters: { id: 2, anotherParam1: 3 },
        })

        expect(deleteBy).toBeCalledWith(Loan, 2)
    })

    it('list to pass through model', async () => {
        listLoans({
            pathParameters: { anotherParam: 3 },
        })

        expect(readAll).toBeCalledWith(Loan)
    })
})

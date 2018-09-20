jest.mock('node-fetch')
jest.mock('../core/crud')
jest.mock('../core/models/loan')

const { Loan } = require('../core/models/loan')
const { create, deleteBy, readAll } = require('../core/crud')
const { createLoan, deleteLoan, listLoans } = require('./functions')
const fetch = require('node-fetch')

describe('testing offering functions', () => {
    it('create to pass through model', async () => {
        fetch.mockResolvedValue({
            ok: true,
            json: jest.fn().mockResolvedValue({
                actief: true,
                companyId: 33,
            }),
        })
        createLoan({
            pathParameters: { anotherParam: 3 },
        })
        expect(fetch).not.toBeCalled()
        expect(create).not.toBeCalled()

        createLoan({
            pathParameters: { amount: 2, anotherParam1: 3 },
        })
        expect(fetch).not.toBeCalled()
        expect(create).not.toBeCalled()

        process.env.OVIO_API_KEY = 'some key'

        await createLoan({
            pathParameters: { amount: 2, companyId: 3 },
        })
        expect(fetch).toHaveBeenCalledWith(
            'https://api.overheid.io/openkvk/3?ovio-api-key=some key'
        )
        expect(create).toHaveBeenCalledWith(Loan, {
            amount: 2,
            company: {
                actief: true,
                companyId: 33,
            },
        })
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

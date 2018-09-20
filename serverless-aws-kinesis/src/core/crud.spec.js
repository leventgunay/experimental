jest.mock('dynamodb')

const dynamo = require('dynamodb')
// const { Loan } = require('./models/loan')
const { db, create, deleteBy, readAll } = require('../core/crud')

describe('testing crud operations', () => {
    beforeEach(() => {
        dynamo.createTables = jest.fn().mockImplementation(cb => {
            cb()
        })
    })

    it('dynamo config to be updated', () => {
        expect(dynamo.AWS.config.update).toBeCalledWith({
            region: 'localhost',
            endpoint: 'http://localhost:8000',
        })
    })

    it('tables to be created', () => {
        db().then(result => {
            expect(result).toBeUndefined()
            expect(dynamo.createTables).toHaveBeenCalledTimes(1)
        })
    })

    it('tables to be created by given db operation', () => {
        const dbOperationCallback = jest.fn().mockImplementation(res => {
            res({ test: 1 })
        })
        db(dbOperationCallback).then(result => {
            expect(result).toMatchObject({ test: 1 })
            expect(dynamo.createTables).toHaveBeenCalledTimes(1)
            expect(dbOperationCallback).toHaveBeenCalledTimes(1)
        })
    })

    it('create to call through', () => {
        jest.unmock('dynamodb')
        jest.resetModules()
        const dynamoHere = require('dynamodb')
        dynamoHere.createTables = dynamo.createTables

        const { Loan } = require('./models/loan')
        let callback = null
        Loan.create = jest.fn().mockImplementation((value, cb) => {
            callback = cb
            cb(undefined, {
                field1: 3,
            })
        })

        create(Loan, 2).then(result => {
            expect(result).toMatchObject({
                statusCode: 200,
                body: JSON.stringify({
                    field1: 3,
                }),
            })
            expect(dynamoHere.createTables).toHaveBeenCalledTimes(1)
            expect(Loan.create).toHaveBeenCalledWith(2, callback)
        })
    })

    it('delete to pass through model', () => {
        jest.unmock('dynamodb')
        jest.resetModules()
        const dynamoHere = require('dynamodb')
        dynamoHere.createTables = dynamo.createTables

        const { Loan } = require('./models/loan')
        let callback = null
        Loan.destroy = jest.fn().mockImplementation((by, opt, cb) => {
            callback = cb
            cb(undefined, {
                field1: 3,
            })
        })

        deleteBy(Loan, 2).then(result => {
            expect(result).toMatchObject({
                statusCode: 200,
                body: JSON.stringify({
                    field1: 3,
                }),
            })
            expect(dynamoHere.createTables).toHaveBeenCalledTimes(1)
            expect(Loan.destroy).toHaveBeenCalledWith(2, { ReturnValues: 'ALL_OLD' }, callback)
        })
    })

    it('list to pass through model', () => {
        jest.unmock('dynamodb')
        jest.resetModules()
        const dynamoHere = require('dynamodb')
        dynamoHere.createTables = dynamo.createTables

        const { Loan } = require('./models/loan')

        Loan.scan = jest.fn().mockImplementation(() => {
            return Loan
        })

        Loan.loadAll = jest.fn().mockImplementation(() => {
            return Loan
        })
        const result = {
            Items: [{ field1: 3 }, { field1: 4 }],
        }

        Loan.exec = jest.fn().mockImplementation(cb => {
            cb(undefined, result)
        })

        readAll(Loan).then(list => {
            expect(list).toMatchObject({
                statusCode: 200,
                body: JSON.stringify([{ field1: 3 }, { field1: 4 }]),
            })
        })
    })
})

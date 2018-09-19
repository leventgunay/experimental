const { create, deleteBy, readAll } = require('../core/crud')
const { Loan } = require('../core/models/loan')

module.exports.createLoan = async event => {
    const { amount } = event.pathParameters

    return create(Loan, { amount })
}

module.exports.deleteLoan = async event => {
    const { id } = event.pathParameters

    return deleteBy(Loan, id)
}

module.exports.listLoans = async () => {
    return readAll(Loan)
}

module.exports.disburseLoan = async () => {
    return {
        statusCode: 501,
        body: 'Not Implemented',
    }
}

module.exports.disburseComplete = async () => {
    // I'll make sure the status is set to disbursed given a LoanDisbursed event
}

const fetch = require('node-fetch')
const { create, deleteBy, readBy, readAll } = require('../core/crud')
const { Loan } = require('../core/models/loan')
const { put } = require('../core/streaming')
const { ok, error } = require('../core/util')

module.exports.createLoan = async ({ pathParameters }) => {
    const { amount, companyId } = pathParameters

    return (
        amount &&
        companyId &&
        fetch(
            `https://api.overheid.io/openkvk/${companyId}?ovio-api-key=${process.env.OVIO_API_KEY}`
        ).then(res => {
            if (res.ok) {
                return res.json().then(company => {
                    if (company.actief) {
                        return create(Loan, { amount, company })
                    } else {
                        return error(400, 'Company is not active.')
                    }
                })
            } else {
                return error(400, 'Company not found by the given id.')
            }
        })
    )
}

module.exports.deleteLoan = async ({ pathParameters }) => {
    const { id } = pathParameters

    return deleteBy(Loan, id)
}

module.exports.listLoans = async () => {
    return readAll(Loan)
}

module.exports.disburseLoan = ({ pathParameters }, context, callback) => {
    const { id } = pathParameters
    // look up for the loan
    readBy(Loan, id).then(res => {
        if (res.ok) {
            // stream
            put(JSON.stringify(pathParameters))
                .then(() => {
                    callback(
                        null,
                        ok({
                            message: 'Disbursement started.',
                        })
                    )
                })
                .catch(err => {
                    console.log(err)
                    callback(null, error(500, 'Couldnt initiate loan disbursement.'))
                })
        } else {
            callback(null, res)
        }
    })
}

module.exports.disburseComplete = async () => {
    // I'll make sure the status is set to disbursed given a LoanDisbursed event
}

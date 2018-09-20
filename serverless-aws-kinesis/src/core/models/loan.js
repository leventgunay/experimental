const Joi = require('joi')
const dynamo = require('dynamodb')

const OFFERED = 'offered'
const DISBURSED = 'disbursed'

const Loan = dynamo.define('Loan', {
    hashKey: 'id',
    timestamps: true,
    schema: {
        id: dynamo.types.uuid(),
        amount: Joi.number().required(),
        company: Joi.object().required(),
        status: Joi.string()
            .default(OFFERED)
            .allow(OFFERED, DISBURSED),
    },
})

module.exports = {
    Loan,
    OFFERED,
    DISBURSED,
}

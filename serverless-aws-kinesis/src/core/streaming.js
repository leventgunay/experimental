const { Kinesis } = require('dynamodb').AWS

const DISBURSING_STREAM = 'DisbursingStream'

const kinesis = new Kinesis({
    region: 'localhost',
    endpoint: 'http://localhost:3000',
    apiVersion: 'latest',
    sslEnabled: false,
})

const put = (data = '', cb = () => {}) => {
    const req = kinesis.putRecord(
        {
            Data: data,
            PartitionKey: DISBURSING_STREAM,
            StreamName: DISBURSING_STREAM,
        },
        cb
    )

    console.log(req)
    return req.promise()
}

module.exports = {
    DISBURSING_STREAM,
    kinesis,
    put,
}

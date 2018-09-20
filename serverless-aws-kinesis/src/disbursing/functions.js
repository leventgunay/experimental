module.exports.disbursingLoan = async ({ Records }, context, callback) => {
    Records.forEach(record => {
        // const payload = new Buffer(record.kinesis.data, 'base64').toString('ascii')
        console.log('Received a Kinesis event: ' + record)
    })

    callback(null, `Successfully processed ${Records.length} Kinesis events`)
}

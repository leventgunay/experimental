const dynamo = require('dynamodb')

dynamo.AWS.config.update({
    region: 'localhost',
    endpoint: 'http://localhost:8000',
})

function db(cb) {
    try {
        return new Promise((res, rej) => {
            dynamo.createTables(err => {
                if (err) {
                    console.log('Error: dynamodb createTables.')
                    rej(err)
                } else if (cb) {
                    cb(res, rej)
                } else {
                    res()
                }
            })
        })
    } catch (e) {
        return {
            statusCode: 500,
            body: e.stack,
        }
    }
}

module.exports.create = (model, value) => {
    if (model && value) {
        return db((res, rej) => {
            model.create(value, function(err, obj) {
                if (err) {
                    console.log('Error: dynamodb create')
                    rej(err)
                }

                res({
                    statusCode: 200,
                    body: JSON.stringify(obj),
                })
            })
        })
    }
}

module.exports.deleteBy = (model, by) => {
    if (model && by) {
        return db(res => {
            model.destroy(by, { ReturnValues: 'ALL_OLD' }, function(err, obj) {
                if (!err) {
                    res({
                        statusCode: 200,
                        body: JSON.stringify(obj),
                    })
                } else {
                    res({
                        statusCode: 404,
                        body: 'Entity not found',
                    })
                }
            })
        })
    }
}

module.exports.readAll = model => {
    if (model) {
        return db((res, rej) => {
            model
                .scan()
                .loadAll()
                .exec((err, list) => {
                    if (err) {
                        rej(err)
                    } else {
                        res({
                            statusCode: 200,
                            body: JSON.stringify(list.Items),
                        })
                    }
                })
        })
    }
}

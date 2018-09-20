const dynamo = require('dynamodb')
const { ok, error } = require('../core/util')

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
        }).catch(e => {
            return error(400, e.stack)
        })
    } catch (e) {
        return error(500, e.stack)
    }
}

module.exports.db = db

module.exports.create = (model, value) => {
    if (model && value) {
        return db((res, rej) => {
            model.create(value, function(err, obj) {
                if (err) {
                    console.log('Error: dynamodb create')
                    rej(err)
                }

                res(ok(obj))
            })
        })
    }
}

module.exports.deleteBy = (model, by) => {
    if (model && by) {
        return db(res => {
            model.destroy(by, { ReturnValues: 'ALL_OLD' }, function(err, obj) {
                if (!err && obj) {
                    res(ok(obj))
                } else {
                    res(error(404, 'Entity not found'))
                }
            })
        })
    }
}

module.exports.readBy = (model, by) => {
    if (model) {
        return db((res, rej) => {
            model.query(by).exec((err, result) => {
                if (err) {
                    rej(err)
                } else {
                    if (result.Count) {
                        res(ok(result.Items[0]))
                    } else {
                        res(error(404, 'Entity not found'))
                    }
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
                        res(ok(list.Items))
                    }
                })
        })
    }
}

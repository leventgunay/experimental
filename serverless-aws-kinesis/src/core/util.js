function ok(result) {
    return {
        ok: true,
        statusCode: 200,
        body: JSON.stringify(result),
    }
}

function error(code, stack) {
    return {
        statusCode: code,
        body: JSON.stringify({ message: stack }),
    }
}

module.exports = {
    ok,
    error,
}

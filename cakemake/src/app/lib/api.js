
export const ENDPOINT_ALL_CAKES = "/api/cakes"

const FAVORITE_CAKES = []

export default class API {



    static getAllCakes() {
        return API.get(ENDPOINT_ALL_CAKES)
    }

    static getFavoriteCakes() {
        return FAVORITE_CAKES
    }

    static isFavorite(cake) {
        return cake && cake.id && FAVORITE_CAKES.findIndex((c) => {
            return c.id === cake.id
        }) > -1
    }

    static addFavorite(cake) {
        FAVORITE_CAKES.push(cake)
    }

    static removeFavorite(cake) {
        if (API.isFavorite(cake)) {
            const ind = FAVORITE_CAKES.findIndex((c) => {
                return c.id === cake.id
            })

            FAVORITE_CAKES.splice(ind, 1)
        }
    }

    static json(url, opts = {}) {
        return fetch(url, opts).then(res => res && res.json())
    }

    static get(url, opts = {}) {
        return API.json(url, {
            ...opts,
            method: "GET"
        }).then((data => data._embedded))
    }

    static post(url, data = {}, opts = {}) {
        return API.json(url, {
            ...opts,
            method: "POST",
            body: JSON.stringify(data)
        })
    }
}
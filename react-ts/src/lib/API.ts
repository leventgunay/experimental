import Episode from "../types/Episode";
import Show from "../types/Show";

export default class API {

    public static BASE_URL = 'http://api.tvmaze.com'

    public static get<T>(url: string): Promise<T> {
        return fetch(API.BASE_URL + url)
            .then((res) => {
                return res.json() as Promise<T>
            })
    }

    public static getShows(): Promise<Show[]> {
        return API.get<any[]>('/search/shows?q=girls')
            .then((res) => {
                return res.map((each: any) => {
                    return each.show as Show
                })
            })
    }

    public static getShowById(id: string) {
        return API.get<Show>('/shows/' + id + '?embed=episodes').then((show: any) => {
            show.episodes = show._embedded.episodes
            return show
        })
    }

    public static getEpisodesByShow({ id }: Show) {
        return API.get<Episode[]>('/shows/' + id + '/episodes')
    }
}
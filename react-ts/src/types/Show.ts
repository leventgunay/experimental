import Episode from "./Episode";
import TVEntertainment from "./TVProduction";

export default class Show extends TVEntertainment {

    public type: string
    public score: number
    public genres: string[]
    public status: string
    public language: string
    public episodes: Episode[]
    public rating: {
        average: number
    }
    public network: {
        name: string
    }

}
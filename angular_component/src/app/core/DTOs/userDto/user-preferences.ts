import { Book } from "../../model/book/book";
import { User } from "../../model/user/user";

export class UserPreferences {

    constructor(public id: Number, public userAccount: User, public favoriteBooks: Book[]) { }

}


import { Author } from "../../model/author/author";
import { Book } from "../../model/book/book";
import { Copy } from "../../model/copy/copy";

export class BookFullInformation {

    public minCopyPrice : Copy | undefined; 
    public maxCopyPrice : Copy | undefined; 
    public isFavorite: Boolean | undefined;

    constructor(public book: Book, public copies: Copy[], public authors: Author[]){ }
    
}

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { BookFullInformation } from "../../DTOs/bookDto/book-full-information";
import { Author } from "../../model/author/author";
import { Book } from "../../model/book/book";
import { Copy } from "../../model/copy/copy";


@Injectable({providedIn: 'root'})
export class BookService {

    private httpOptions= {
        headers: new HttpHeaders({
          "Content-Type": "application/json"
        })
    }

    constructor(private httpClient: HttpClient) { }

    getBookList(): Observable<BookFullInformation[]>{
        return this.httpClient.get<BookFullInformation[]>("http://localhost:8080/book/list");
    }

    getBookById(id: Number): Observable<BookFullInformation>{
        return this.httpClient.get<BookFullInformation>("http://localhost:8080/book/view/" + id);
    }

    getCopiesByBookId(id: Number): Observable<Copy[]>{
        return this.httpClient.get<Copy[]>("http://localhost:8080/book/ " + id +"/copies");
    }

    
    getRecommendedBooks(): Observable<Book[]>{
        return this.httpClient.get<Book[]>("http://localhost:8080/book/recommended");
    }

    getAuthorsByBookId(id: Number): Observable<Author[]>{
        return this.httpClient.get<Author[]>("http://localhost:8080/book/" + id + "/authors");
    }
}
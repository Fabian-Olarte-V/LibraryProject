import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map, Observable } from "rxjs";
import { BookFullInformation } from "../../DTOs/bookDto/book-full-information";
import { Book } from "../../model/book/book";
import { Copy } from "../../model/copy/copy";
import { Credentials } from "../../model/credential/credentials";
import { User } from "../../model/user/user";


@Injectable({providedIn: 'root'})
export class UserService {

    private httpOptions= {
        headers: new HttpHeaders({
          "Content-Type": "application/json"
        })
    }

    constructor(private httpClient: HttpClient) { }

    loginService(cred: Credentials){
        return this.httpClient.post("http://localhost:8080/login", cred, {observe: 'response'})
                    .pipe(map((response: HttpResponse<any>) => {
                        const body = response.body;
                        const headers = response.headers;
                        const token = headers.get("Authorization")!.replace('Bearer ', '');

                        localStorage.setItem('token', token);
                        localStorage.setItem('username', cred.email.toString());

                        this.getCurrentAccount().subscribe(account => {
                            localStorage.setItem('idUser', account.id.toString())
                        })

                        return body;
                    }));
    }

    getToken(){
        return localStorage.getItem('token');
    }

    getCurrentUserEmail(){
        return localStorage.getItem('username');
    }

    getCurrentAccount(): Observable<User>{
        return this.httpClient.get<User>("http://localhost:8080/user/account?userEmail=" + this.getCurrentUserEmail());
    }

    getCurrentUserId(){
        return localStorage.getItem('idUser');
    }

    getFavoriteBooks(): Observable<BookFullInformation[]>{
        return this.httpClient.get<BookFullInformation[]>("http://localhost:8080/user/"+ this.getCurrentUserId() + "/favorite-books");
    }

    addFavoriteBook(book: Book): Observable<any>{
        return this.httpClient.put<any>("http://localhost:8080/user/" + this.getCurrentUserId()  + "/add-favorite", book.id, this.httpOptions);
    }

    removeFavoriteBook(book: Book): Observable<any>{
        return this.httpClient.delete<any>("http://localhost:8080/user/" + this.getCurrentUserId()  + "/remove-favorite?bookId=" + book.id, this.httpOptions);
    }

    getShoppingCart(): Observable<Copy[]>{
        return this.httpClient.get<Copy[]>("http://localhost:8080/user/" + this.getCurrentUserId() + "/shoppingCart");
    }

    addCopyToShoppingCart(copy : Copy): Observable<any>{
        return this.httpClient.put<any>("http://localhost:8080/user/" + this.getCurrentUserId() + "/add-shoppingCart", copy.id, this.httpOptions);
    }

    removeCopyFromShoppingCart(copy: Copy): Observable<any>{
        return this.httpClient.delete<any>("http://localhost:8080/user/" + this.getCurrentUserId() + "/remove?copyId=" + copy.id, this.httpOptions);
    }

    getRecommendedBooks(): Observable<BookFullInformation[]>{
        return this.httpClient.get<BookFullInformation[]>("http://localhost:8080/user/" + this.getCurrentUserId() + "/recommended-books");
    }

    
}

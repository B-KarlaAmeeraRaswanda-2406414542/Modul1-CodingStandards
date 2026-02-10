Karla Ameera Raswanda (Adpro B, 2406414542)

## Reflection 1

### Clean Code Principles
1. **Separation of Concerns**
    - Logic dipisah menjadi model, repository, controller, dan service.
2. **Single Responsibility**
    - Model untuk domain object (Product), Repository untuk pengelolaan data (in-memory), Controller untuk request/response dan view routing, dan Service untuk business logic.
3. **Readable Naming**
    - Nama class dan method mengikuti konteks fitur (create, list, edit, delete).
4. **Small Commits / Incremental Changes**
    - Perubahan dibuat bertahap per fitur/layer untuk mengurangi conflict.

### Secure Coding Practices
1. **Avoid exposing internal implementation**
    - Endpoint hanya expose operasi yang diperlukan di tutorial.
2. **Input handling**
    - Data product  dari form, validasi belum diterapkan.

### Improvements / What I would do next
1. **Add Validation**
    - Menambahkan validasi sederhana (mis. id tidak kosong, quantity >= 0).
2. **Handle Not Found Case**
    - Saat edit/delete id tidak ditemukan, sebaiknya ada handling (mis. redirect dengan pesan).
3. **Testing**
    - Menambahkan unit test untuk repository/service.

## Reflection 2

### Question 1

Setelah menulis unit test, saya merasa lebih aman untuk melanjutkan pekerjaan dari satu fitur ke fitur lain. Sebab, di unit test, tiap fungsi diuji secara terpisah sehingga kesalahan dapat terdeteksi lebih awal. 

Tidak ada jumlah pasti berapa banyak unit test dalam satu Class. Yang terpenting adalah unit test dapat mencakup behaviors utama, termasuk skenario positif dan negatif.

Menurut saya, memiliki 100% code coverage tidak menjamin bahwa programnya bebas dari bug atau error. Masih mungkin terdapat kesalahan logic atau edge case yang tidak terdeteksi.

### Question 2

Jika setelah membuat `CreateProductFunctionalTest` saya diminta membuat functional test lain, maka code cleanliness bisa menurun (ada duplikasi).

Duplikasi code bisa menimbulkan masalah ketika terjadi perubahan di setup, karena perubahan tersebut harus dilakukan di banyak tempat.

Menurut saya, sebaiknya, logic setup yang sama dapat di-extract ke base test class yang bisa digunakan kembali oleh test-test lain.
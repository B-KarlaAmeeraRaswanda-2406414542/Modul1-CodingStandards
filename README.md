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
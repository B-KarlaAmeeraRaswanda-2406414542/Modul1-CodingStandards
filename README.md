Karla Ameera Raswanda (Adpro B, 2406414542)

## Reflection 2

### Question 1

Setelah menulis unit test, saya merasa lebih aman untuk melanjutkan pekerjaan dari satu fitur ke fitur lain. Sebab, di unit test, tiap fungsi diuji secara terpisah sehingga kesalahan dapat terdeteksi lebih awal. 

Tidak ada jumlah pasti berapa banyak unit test dalam satu Class. Yang terpenting adalah unit test dapat mencakup behaviors utama, termasuk skenario positif dan negatif.

Menurut saya, memiliki 100% code coverage tidak menjamin bahwa programnya bebas dari bug atau error. Masih mungkin terdapat kesalahan logic atau edge case yang tidak terdeteksi.

### Question 2

Jika setelah membuat `CreateProductFunctionalTest` saya diminta membuat functional test lain, maka code cleanliness bisa menurun (ada duplikasi).

Duplikasi code bisa menimbulkan masalah ketika terjadi perubahan di setup, karena perubahan tersebut harus dilakukan di banyak tempat.

Menurut saya, sebaiknya, logic setup yang sama dapat di-extract ke base test class yang bisa digunakan kembali oleh test-test lain.
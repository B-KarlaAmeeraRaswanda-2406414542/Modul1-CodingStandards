Karla Ameera Raswanda (Adpro B, 2406414542)

## Reflection - Module 1

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

### Question 1

Setelah menulis unit test, saya merasa lebih aman untuk melanjutkan pekerjaan dari satu fitur ke fitur lain. Sebab, di unit test, tiap fungsi diuji secara terpisah sehingga kesalahan dapat terdeteksi lebih awal.
Tidak ada jumlah pasti berapa banyak unit test dalam satu Class. Yang terpenting adalah unit test dapat mencakup behaviors utama, termasuk skenario positif dan negatif.
Menurut saya, memiliki 100% code coverage tidak menjamin bahwa programnya bebas dari bug atau error. Masih mungkin terdapat kesalahan logic atau edge case yang tidak terdeteksi.

### Question 2

Jika setelah membuat `CreateProductFunctionalTest` saya diminta membuat functional test lain, maka code cleanliness bisa menurun (ada duplikasi).
Duplikasi code bisa menimbulkan masalah ketika terjadi perubahan di setup, karena perubahan tersebut harus dilakukan di banyak tempat.
Menurut saya, sebaiknya, logic setup yang sama dapat di-extract ke base test class yang bisa digunakan kembali oleh test-test lain.

## Reflection - Module 2

### 1. Code Quality Issues Fixed

During this exercise, I fixed several PMD issues:
- Removed unused imports.
- Simplified conditional logic to reduce complexity.
- Adjusted code formatting to follow clean code principles.

My strategy was to first analyze the PMD report generated in GitHub Actions.
Then, I categorized issues into structural problems (like unused imports) and readability issues.

### 2. CI/CD Evaluation

The current implementation fulfills the definition of Continuous Integration because:
- Every push triggers automated testing.
- Code quality analysis runs automatically.
- Build fails if tests fail.

It also implements Continuous Deployment since:
- The Docker image is automatically built and pushed to ECR.
- Deployment workflow is triggered without manual intervention.

## Reflection - Module 3

### Explain what principles you apply to your project!

In this project, I applied all five SOLID principles:

**1. Single Responsibility Principle (SRP)**

Each class has a single responsibility:
* Controller handles HTTP requests and responses.
* Service encapsulates business logic.
* Repository manages data persistence.
* Model represents data structure.

**2. Open-Closed Principle (OCP)**

Classes are open for extension but closed for modification.
For example, controllers depend on the CarService and ProductService interfaces rather than concrete implementations. If a new implementation (e.g., caching or database-based service) is introduced, it can replace the current implementation without modifying controller code.

**3. Liskov Substitution Principle (LSP)**

 Implementations of CarService and ProductService respect their interface contracts. Any implementation can replace another without affecting program correctness. For instance, CarServiceImpl fulfills all method contracts defined in CarService.

**4. Interface Segregation Principle (ISP)**

Service interfaces are cohesive and contain only relevant CRUD methods. No class is forced to implement methods it does not use. For example, ProductService only defines operations related to product management.

**5. Dependency Inversion Principle (DIP)**

High-level modules (controllers) depend on abstractions (Service interfaces), not concrete implementations. For example: Instead of depending on CarServiceImpl, the controller depends on the abstraction.

### Explain the advantages of applying SOLID principles to your project with examples.

* Because responsibilities are separated, modifying business logic in Service does not impact the Controller layer, so the risk of unintended side effects is reduced.
* By applying OCP, new service implementations can be introduced without modifying existing controller code.
* Loose coupling allows mocking service interfaces during unit testing. Controllers can be tested independently from repository logic.
* Layer separation (Controller → Service → Repository) makes the system easier to understand and reason about.

### Explain the disadvantages of not applying SOLID principles to your project with examples.

* If controllers directly depended on concrete classes (e.g., CarServiceImpl), changing implementation details would require modifying multiple classes.
* If business logic and HTTP logic were mixed in one class, any change in business rules could break web behavior.
* Without OCP, adding new features would require modifying existing classes.
* Without abstraction (DIP), mocking dependencies in unit tests would be difficult.

## Reflection - Module 4

### 1. Is the TDD workflow useful for achieving your testing objectives?

The Test-Driven Development (TDD) workflow used in this module is useful because it forces developers to clearly understand requirements before implementing functionality. The red-green-refactor cycle also helps maintain code quality, since developers must continuously run tests and refactor code without breaking functionality. In this exercise, TDD helped ensure that each component (model, repository, and service) behaved as expected and that regressions could be detected immediately.

### 2. Have your tests followed the F.I.R.S.T. principles?

Overall, the tests created in this module generally follow the F.I.R.S.T. principles. The tests are **Fast** because they are unit tests that run quickly without relying on external systems. They are **Independent** since each test method initializes its own setup and does not depend on the result of other tests. They are also **Repeatable** because they can be executed multiple times with consistent results. The tests are **Self-validating** as they use assertions to automatically determine whether the behavior is correct or not.
package project1;

enum Category implements ProductFactory {
        KIDS {
            @Override public Product create(String name, float price) {
                return Product.of(name, price, this);
            }
        },
        OFFICE   { @Override public Product create(String n, float p) { return Product.of(n, p, this); } },
        ELECTRONICS {
            @Override public Product create(String name, float price) {
                return Product.of(name, price, this);
            }
        },
        CLOTHING { @Override
        public Product create(String n, float p) { return Product.of(n, p, this); } };


    }



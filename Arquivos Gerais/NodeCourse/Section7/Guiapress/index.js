const express = require("express");
const app = express();
const bodyParser = require("body-parser");

const connection = require("./database/database");

const Article = require("./articles/Articles");
const Category = require("./categories/Category");


const PORT = 8082;

const categoriesController = require("./categories/CategoriesController");
const articlesController = require("./articles/ArticlesController");
const userController = require ("./user/UserController");

const { default: slugify } = require("slugify");

app.get("/", (req,res) =>{
   Article.findAll({
    order:[
      ['id','DESC']
    ]
   })
     .then(articles => {
      Category.findAll().then(categories => {
      res.render("home", {articles: articles, categories,categories});
     });
     }); 
     // res.render("home");
});

app.get("/:slug", (req,res)=>{
  var slug = req.params.slug;
  Article.findOne({ where:  {slug: slug}
     })
    .then(article => {
      if(article != undefined){
           Category.findAll().then(categories => {
             res.render("admin/articles/article", {article: article, categories,categories});
        }); 
      } else{
        res.redirect("/");
      }  
    }).catch( err => {
      res.redirect("/");
    })
    // res.render("home");
});

app.get("/admin/category/:slug", (req,res)=> {
     var slug = req.params.slug;
     console.log("Slug encontrando. " + slug)
     Category.findOne({
      where: {
           slug: slug
      } ,
       include: [{model: Article}]
      }).then(category => {
        if(category != undefined){

          Category.findAll().then(categories => {
            res.render("home", {articles: category.articles, categories: categories})
          })

        }else {
          res.redirect("/");
        }
      }).catch(error =>{
             res.redirect("/");
      }) 
     })


app.set('view engine','ejs');
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json())
app.use(express.static('public'));

app.use("/",userController);
app.use("/",categoriesController);
app.use("/",articlesController);



connection.authenticate()
          .then(() => {
                       console.log("Databasse connection successfull!");
                       app.listen(PORT, () =>{
                        console.log("Server up and running on port: ", PORT);
                    });
          }).catch((error)=> {
        console.log(error);
    })


const express = require("express");
const router = express.Router();

const Category = require("../categories/Category");
const Articles = require("./Articles");
const Slugify = require("slugify");
const { default: slugify } = require("slugify");
const Article = require("./Articles");

router.get("/admin/articles", (req, res) => {
    Articles.findAll()
      .then(articles => {
        res.render("admin/articles/list", {articles: articles});    
      })
})  


 router.get("/admin/articles/list", (req,res)=>{
    Articles.findAll({
        include: [{model: Category}]
    }).then(articles => {
        res.render("admin/articles/list", {articles: articles})

    })
   
})

 router.get("/admin/articles/new", (req,res)=> {
     Category.findAll()
    .then(categories  => {
    res.render("admin/articles/new", {category: categories})
    })
 })

router.post("/admin/articles/save", (req, res) => {
    var title = req.body.title;
    var body = req.body.body;
    var category = req.body.category;

    Articles.create({
                      title: title,
                      slug: slugify(title),
                      body: body,
                      categoryId: category
                   }).then(()=> {
                                 res.redirect("/admin/articles/list")
    })
})


router.post("/admin/articles/delete",(req,res)=>{
    var id = req.body.id;
    if(id != undefined){
       if(!isNaN(id)){
           Articles.destroy ({
                 where: { id: id}
        }).then(() => {
                 res.redirect("/admin/articles/list");
        })
       }
       else{
            console.log("Id inválido")
       }
    }else
    {
        console.log("Id inválido!")
    }
});

router.get("/admin/articles/edit/:id", (req, res)=> {
    var id = req.params.id;
    Articles.findByPk(id).then(article => {
        if(article != undefined){
            Category.findAll().then(category => {
                res.render("admin/articles/edit", {category: category, article: article})
            });               
        }else {
                res.redirect("/")
            }

    }).catch(err => {
        res.redirect("/")
    })
});

router.post("/admin/articles/update", (req,res) => {
    var id = req.body.id;
    var title = req.body.title;
    var body = req.body.body;
    var category = req.body.category;

    Article.update({ title: title, body: body, categoryId: category, slug: slugify(title)}, {
        where: {
            id: id
        }
        }).then(()=> {
              res.redirect("/admin/articles");
        }).catch(err => {
            res.redirect("/");
        })
    })

router.get("/admin/articles/paging/:num", (req,res) => {
    var page = req.params.num;
    var offset = 0 ;

    if(isNaN(page) || page == 1){
        offset = 0;
    }else {
        offset = (parseInt(page) -1) * 4;
    }

    Article.findAndCountAll({
        limit: 2,
        offset: offset
    }).then(articles => {
        var next;
        if(offset + 4 >= articles.count){
            next = false;
        }else{
            next = true;
        }
        var result = {
            page: parseInt(page),
            next: next,
            articles : articles
        }

        Category.findAll().then(categories => {
          res.render("admin/articles/page", {result: result, categories: categories})
        });

       // res.json(result);
    })


})    





module.exports =  router;
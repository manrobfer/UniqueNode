const express = require('express');
const router = express.Router();
const Category = require("./Category");
const slugify = require("slugify");

router.get("/categories", (req,res) => {
    res.send("testando categories")
});

router.get("/admin/categories/new", (req,res)=>{
    res.render("admin/categories/new")
});

router.post("/admin/categories/delete",(req,res)=>{
    var id = req.body.id;
    if(id != undefined){
       if(!isNaN(id)){
        Category.destroy ({
           where: { id: id}
        }).then(() => {
            res.redirect("/admin/categories/list");
        })
       }
       else{
        console.log("Id inválido")
       }
    }else
    {
        console.log("Id inválido!")
    }
})

router.post("/admin/categories/save", (req,res)=>{
    var title = req.body.title;

    if(title != undefined){

        Category.create({
            title: title,
            slug: slugify(title)
        }).then(()=> {
            res.redirect("/admin/categories/list");
        });

    } else {
        res.redirect("/admin/categories/new");
    }
   
});

router.get("/admin/categories/list", (req,res)=>{
    Category.findAll().then(categories => {
        res.render("admin/categories/list", {categories: categories});
    });
   
})

router.post("/admin/categories/update",(req,res)=>{
    var id = req.body.id;
    var title = req.body.title;

    Category.update({title: title, slug: slugify(title)},{
        where: {id: id}
    }).then(()=> {
        res.redirect("/admin/categories/list");
    })
})

router.get("/admin/categories/edit/:id", (req, res)=> {
    var id = req.params.id;
    console.log("Id retornado: "+id);
    if(isNaN(id)){
        res.redirect("admin/categoires")
    }

    Category.findByPk(id).then(category => {
        if(category != undefined){
            res.render("admin/categories/edit",{categories: category})
        } else {
            res.redirect("/admin/categories/edit");
        }
    }).catch(error => {
        console.log("Erro ao buscar id: "+ id + " "+error)
        res.redirect("admin/categories");
    })
});

module.exports = router;
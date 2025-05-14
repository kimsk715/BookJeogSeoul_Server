const kdcValue = document.querySelectorAll(".ddc")


kdcValue.forEach((category) => {
    category.addEventListener('click', () => {
        const kv = category.valueOf()
        console.log(kv);
        categoryLayout.books(categoryBookSerivce.selectByKdc(kv))
    })
})
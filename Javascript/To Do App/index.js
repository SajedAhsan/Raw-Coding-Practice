const form = document.querySelector('form');
const tasks = document.getElementById("tasks");
form.addEventListener('submit',(obj)=>{
    obj.preventDefault();
    const task = document.getElementById("text");
    let s = task.value;
    const parent = document.createElement('tr');
    const child1 = document.createElement('td');
    const child2 = document.createElement('td');
    const doneButton = document.createElement('button');
    const deleteButton = document.createElement('button');
    child1.textContent = s;
    doneButton.textContent = "Done";
    doneButton.classList.add("done");
    deleteButton.classList.add("delete");
    deleteButton.textContent = "Delete";
    child2.append(doneButton,deleteButton);
    parent.append(child1,child2);
    tasks.append(parent);
    form.reset();
    doneButton.addEventListener('click',()=>{
        child1.style.textDecoration = "line-through";
    });
    deleteButton.addEventListener('click',()=>{
        parent.remove();
    });
});
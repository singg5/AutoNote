const app = document.getElementById('app')
function Header() {
    return (<h1>Develop. Preview. Ship. 🚀</h1>)
}
function HomePage() {
    return (
        <div>
            <Header />
            <Header />
            <Header />
            <Header />
            <p>lol</p>
        </div>
    );
}

ReactDOM.render(<HomePage />, app)
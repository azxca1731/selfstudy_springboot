import React, { useState } from "react";
import styled from "styled-components";
import Post from "./Post";
import Input from "./Input";
import axios from "axios";

const AppDiv = styled.div`
	padding: 30px;
	min-height: 100vh;
	background-color: skyblue;
	width: 100vw;
`;

function App() {
	const [appState, setState] = useState({
		input: "",
		loading: false,
		posts: []
	});
	const getPosts = async () => {
		setState({
			...appState,
			loading: true
		});
		const value = await axios.get("localhost:8080/posts");
		console.log(value);
	};
	return (
		<AppDiv>
			<h2 className="text-primary text-center mb-4">Timeliner!</h2>
			<Input
				changeState={setState}
				getPosts={getPosts}
				appState={appState}
			/>
			{appState.posts.map(index => (
				<Post key={index} />
			))}
		</AppDiv>
	);
}

export default App;
